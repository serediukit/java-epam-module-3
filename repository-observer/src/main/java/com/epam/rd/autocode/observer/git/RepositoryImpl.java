package com.epam.rd.autocode.observer.git;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RepositoryImpl implements Repository {
    private final List<Branch> branches = new ArrayList<>();
    private final List<WebHook> webHooks = new ArrayList<>();

    public RepositoryImpl() {
        branches.add(new Branch("main"));
    }

    @Override
    public Branch getBranch(String name) {
        return branches.stream()
                .filter(b -> b.getName().equals(name))
                .findAny()
                .orElse(null);
    }

    @Override
    public Branch newBranch(Branch sourceBranch, String name) {
        if(getBranch(name) != null || getBranch(sourceBranch.getName()) == null)
            throw new IllegalArgumentException();

        Branch newBranch = new Branch(name);
        newBranch.submit(sourceBranch.getCommits());
        branches.add(newBranch);

        return newBranch;
    }

    @Override
    public Commit commit(Branch branch, String author, String[] changes) {
        Commit commit = new Commit(author, changes);
        branch.submit(List.of(commit));
        updateWebHooks(Event.Type.COMMIT, branch, List.of(commit));
        return commit;
    }

    @Override
    public void merge(Branch sourceBranch, Branch targetBranch) {
        List<Commit> commits = sourceBranch.getCommits().stream()
                .filter(c -> !(targetBranch.getCommits().contains(c)))
                .collect(Collectors.toList());
        targetBranch.submit(commits);
        updateWebHooks(Event.Type.MERGE, targetBranch, commits);
    }

    @Override
    public void addWebHook(WebHook webHook) {
        this.webHooks.add(webHook);
    }

    private void updateWebHooks(Event.Type type, Branch branch, List<Commit> commits) {
        webHooks.forEach(webHook -> {
            if (webHook.type().equals(type) && webHook.branch().equals(branch.getName()) && !commits.isEmpty()) {
                webHook.onEvent(new Event(type, branch, commits));
            }
        });
    }
}