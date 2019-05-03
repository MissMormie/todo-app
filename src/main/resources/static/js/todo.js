// TODO Object

var Todo = function(id, title, status, priority, endDate) {
    this.id = id;
    this.title = title;
    this.status = status;
    this.priority = priority;
    this.endDate = "";
    if(endDate instanceof Date) {
        var month = endDate.getUTCMonth() + 1;
        var day = endDate.getUTCDate();
        var year = endDate.getUTCFullYear();

        this.endDate = year + "-" + month + "-" + day;
        console.log(this.endDate);
    } else {
        this.endDate = endDate;
    }
};

Todo.prototype.constructor = Todo;

Todo.prototype.saveTodo = function(callBackSucces, callBackFailure) {

    console.log(JSON.stringify(this));
    $.ajaxSetup({
       headers:{
         'Content-Type': 'application/json'
       }
    });

    $.post("/todo/" + this.id, JSON.stringify(this))
      .done(callBackSucces)
      .fail(callBackFailure)
}

