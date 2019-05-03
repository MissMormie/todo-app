

$(document).ready(function(){
    $("[data-form='form-field']").on('change keyup paste', function(){
        // update todo
        var todo = createTodo($(this).attr("data-id"));
        todo.saveTodo(saveSuccess(), saveFailed());
    });
});

function createTodo(id) {
//    var status = "todos[" + id "].status;
// "[name='todos[" + id + "].status'"
    var status = $("[name='todos[" + id +"].status'] option:selected").val();
    var title = $("[name='todos[" + id +"].title'] ").val();
    var priority = $("[name='todos[" + id +"].priority'] option:selected").val();
    var endDate = new Date($("[name='todos[" + id +"].endDate'] ").val());
    console.log(endDate);

    return new Todo(id, title, status, priority, endDate);

}

function saveSuccess() {
    console.log("save success")
}

function saveFailed() {
    console.log("save failes")

}