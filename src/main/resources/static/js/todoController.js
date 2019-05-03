/**
* App beginning, calls the TodoController
*/
$(document).ready(function(){
    var todoController = new TodoController();
});


/*
* @Constructor
* Controls the removing and updating of todos on page.
*/
var TodoController = function() {
    this.todoPersistence = new TodoPersistence();
    this.setUpFormListeners();
}

TodoController.prototype.constructor = TodoController;

/*
 * Sets up the form listeners, checks changes in the form and click on delete button.
 */
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
        if (confirm('Weet je zeker dat je deze todo wilt verwijderen? ')) {
            self.todoPersistence.deleteTodo(todo, self.deleteTodoInView(todo), self.saveFailed);
        } 
    })
}

/*
* Removes the todo from the ui.
*/
TodoController.prototype.deleteTodoInView = function(todo) {
    if(!todo instanceof Todo) {
        console.log("Failed to delete todo from View. Wrong object type: " + todo);
        return;
    }

    $("tr[data-id='" + todo.id + "']").remove();
}

/*
* Reads the information from the form and creates a todo object
* return popupated todo object
*/
TodoController.prototype.createTodo = function (id) {
    var status = $("[name='todos[" + id +"].status'] option:selected").val();
    var title = $("[name='todos[" + id +"].title'] ").val();
    var priority = $("[name='todos[" + id +"].priority'] option:selected").val();
    var endDate = new Date($("[name='todos[" + id +"].endDate'] ").val());
    return new Todo(id, title, status, priority, endDate);
}

/*
* Currently only logs save succes, should show a visual clue to the user that content is saved.
*/
TodoController.prototype.saveSuccess = function () {
    console.log("save success")
}

/*
* Currently only logs save failure, should show a visual clue to the user that content is not saved.
*/
TodoController.prototype.saveFailed = function () {
    console.log("save failed")
}