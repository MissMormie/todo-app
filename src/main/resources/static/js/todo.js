/*
* Todo
*/
var Todo = function(id, title, status, priority, endDate) {
    this.id = id;
    this.title = title;
    this.status = status;
    this.priority = priority;
    this.endDate = endDate;
};

Todo.prototype.constructor = Todo;
