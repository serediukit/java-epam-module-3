# Even Index Elements SubList Decorator
**Decorator** is a structural design pattern that lets you attach new behaviors to objects by placing these objects inside special wrapper objects that contain the behaviors.

Implement [`com.epam.rd.autocode.decorator.Decorators`](src/main/java/com/epam/rd/autocode/decorator/Decorators.java) method:
- `evenIndexElementsSubList` - returns a decorator, that manages only the elements with even indices in a source list.\
  Decorator list must support "read" methods: `get()`, `size()`, `iterator()`. No other methods are required.\
  Note that if source list state is changed, the decorator list must follow them as well.