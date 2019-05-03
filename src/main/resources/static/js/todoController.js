
$(document).ready(function(){
    var todoController = new TodoController();
    todoController.setUpFormListeners();
});


var TodoController = function() {
    this.todoPersistence = new TodoPersistence();
}

TodoController.prototype.constructor = TodoController;

TodoController.prototype.setUpFormListeners = function() {
    var self = this;
    // Set form change listener
    $("[data-form='form-field']").on('change keyup paste', function(){
        var todo = self.createTodo($(this).attr("data-id"));
        self.todoPersistence.saveTodo(todo, self.saveSuccess, self.saveFailed);
    });

    // Set delete listener
    $("[data-action='delete']").on('click', function() {
        var todo = self.createTodo($(this).attr("data-id"));
        self.todoPersistence.deleteTodo(todo, self.deleteTodoInView(todo), self.saveFailed);
    })
}

TodoController.prototype.deleteTodoInView = function(todo) {
    if(!todo instanceof Todo) {
        console.log("Failed to delete todo from View. Wrong object type: " + todo);
        return;
    }

    $("tr[data-id='" + todo.id + "']").remove();

}

TodoController.prototype.createTodo = function (id) {
    var status = $("[name='todos[" + id +"].status'] option:selected").val();
    var title = $("[name='todos[" + id +"].title'] ").val();
    var priority = $("[name='todos[" + id +"].priority'] option:selected").val();
    var endDate = new Date($("[name='todos[" + id +"].endDate'] ").val());
    return new Todo(id, title, status, priority, endDate);
}

TodoController.prototype.saveSuccess = function () {
    console.log("save success")
}

TodoController.prototype.saveFailed = function () {
    console.log("save failed")
}