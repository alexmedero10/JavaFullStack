const taskList = document.getElementById("taskList");
const tasks = document.createElement("ul");
taskList.appendChild(tasks);
var index = 1;

const completedTaskList = document.getElementById("completedTaskList");
const completedTask = document.createElement("ul");
completedTaskList.appendChild(completedTask);

const taskCount = document.getElementById("taskCount");
var countTasks = 0;
taskCount.textContent = countTasks;

const searchBar = document.getElementById("searchBar");

const formTask = document.getElementById("formTask");


function validateForm() {
    const inputTask = document.getElementById("inputTask").value;
    const errorMessage = document.getElementById("errorMessage");
    errorMessage.textContent = "";

    if (!inputTask || inputTask == "") {
        errorMessage.textContent = "Task is required";
        return false;
    }

    return true;
}

function addTask() {
    const inputTask = document.getElementById("inputTask").value;
    const taskUL = document.createElement("li");

    taskUL.textContent = inputTask;
    taskUL.setAttribute("id", index++);
    taskUL.classList.add("task");

    const taskCompletedCheckbox = document.createElement("input");
    taskCompletedCheckbox.type = "checkbox";
    taskUL.appendChild(taskCompletedCheckbox);

    const buttonDelete = document.createElement("input");
    buttonDelete.type = "button";
    buttonDelete.value = "Delete"
    taskUL.appendChild(buttonDelete);

    tasks.appendChild(taskUL);
    countTasks++;


    taskCompletedCheckbox.addEventListener('change', function() {
        if (this.checked) {
            const task = document.createElement("li");
            task.textContent = taskUL.textContent;
            task.setAttribute("id", "completed"+taskUL.id);
            taskUL.style.textDecoration = "line-through";
            completedTask.appendChild(task);
            countTasks--;
        } else {
            completedTask.removeChild(document.getElementById("completed"+taskUL.id));
            taskUL.style.removeProperty("text-decoration"); 
        }
    });

    buttonDelete.addEventListener('click', function() {
        const taskCompleted = document.getElementById("completed"+taskUL.id);
        if (taskCompleted) 
            completedTask.removeChild(taskCompleted);
        else countTasks--;
        tasks.removeChild(taskUL);
        updateTaskCount();
    });
}

function updateTaskCount() {
    taskCount.textContent = countTasks;
}


formTask.addEventListener('submit', function(event) {
    event.preventDefault();
    if (validateForm()){
        addTask();
        updateTaskCount();
    }
});

searchBar.addEventListener('input', function() {
    const query = searchBar.value.toLowerCase();

    const items = tasks.querySelectorAll("li");

    items.forEach( item => {
        if (item.textContent.toLowerCase().includes(query)) {
            item.style.display = "";
        } else {
            item.style.display = "none";
        }
    });

});