# User Guide

Duke is a personal Chatbot that helps you manage and keep track of your tasks through a Command Line Interface.

* [Quick Start](#quick-start)
* [Features](#features)
    * [List all tasks: `list`](#1-list---list-all-tasks)
    * [Add new Todo: `todo`](#2-todo---add-new-todo)
    * [Add new Deadline: `deadline`](#3-deadline---add-new-deadline)
    * [Add new Event: `event`](#4-event---add-new-event)
    * [Mark a task as done: `done`](#5-done---mark-a-task-as-done)
    * [Delete a task: `delete`](#6-delete---delete-a-task)
    * [Find tasks based on keyword: `find`](#7-find---find-tasks-based-on-keyword)
    * [Exit program: `bye`](#8-bye---exit-program)
* [Command Summary](#command-summary)

## Quick Start

1. Ensure you have Java 11 or above installed on your computer.

2. Download the latest version of `CS2113_IP.jar`.

3. Copy the file to a folder you want to use as the root directory for Duke.

4. Open a Command Prompt from that folder, and run the following commands:
   ```
   chcp 65001
   java -Dfile.encoding=UTF-8 -jar CS2113_IP.jar
   ```
   
5. Refer to Features for more details on the available commands and their usage.

## Features 

### 1. `list` - List all tasks
Displays an indexed list of all tasks.

##### Example of usage: 
`list`

##### Expected outcome:
```
Here are the tasks in your list:
1.[T][✘] some task 1
2.[D][✓] some task 2 (by: monday)
3.[E][✓] some task 3 (at: sunday night)
```

### 2. `todo` - Add new Todo
Adds a todo task to the list.

##### Example of usage: 
`todo do some coding`

##### Expected outcome:
```
Got it. I've added this task:
	[T][✘] do some coding
Now you have 4 tasks in the list.
```

### 3. `deadline` - Add new Deadline
Adds a deadline to the list. The time can be specified after a `/by` separator.

##### Example of usage: 
`deadline do math homework /by tuesday`

##### Expected outcome:
```
Got it. I've added this task:
	[D][✘] do math homework (by: tuesday)
Now you have 5 tasks in the list.
```

### 4. `event` - Add new Event
Adds an event to the list. The time can be specified after a `/at` separator.

##### Example of usage: 
`event attend zoom lecture /at friday 4pm`

##### Expected outcome:
```
Got it. I've added this task:
	[E][✘] attend zoom lecture (at: friday 4pm)
Now you have 6 tasks in the list.
```

### 5. `done` - Mark a task as done
Marks a task in the list as done, based on the given index.

##### Example of usage: 
`done 1`

##### Expected outcome:
```
Nice! I've marked this task as done:
	[T][✓] some task 1
```

### 6. `delete` - Delete a task
Deletes a task from the list, based on the given index.

##### Example of usage: 
`delete 1`

##### Expected outcome:
```
Noted. I've removed this task:
	[T][✓] some task 1
```

### 7. `find` - Find tasks based on keyword
Finds all tasks in the list that contain the given keyword or string.

##### Example of usage: 
`find coding`

##### Expected outcome:
```
Here are the matching tasks in your list:
1.[T][✘] do some coding
```

### 8. `bye` - Exit program
Terminates the Command Line Interface.

##### Example of usage: 
`bye`

##### Expected outcome:
```
Bye. Hope to see you again soon!
```

## Command Summary

Command | Format | Example
------- | ---------- | ------------
list | `list` | -
todo | `todo [description]` | `todo do some coding`
deadline | `deadline [description] /by [time]`| `deadline do math homework /by tuesday`
event | `event [description] /at [time]` | `attend zoom lecture /at friday 4pm` 
done | `done [index]`  | `done 1`
delete | `delete [index]` | `delete 1`
find | `find [keyword or string]` | `find coding`
bye | `bye` | -


