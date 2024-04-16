# infinitecounters
#### Video Demo:  <https://youtu.be/Ejq7xY3_IsA>
#### Infinite Counters: Android app with Java, XML, SQLite for 3 counter types &amp; task list with subtasks. Built in Android Studio.

There are four things the user could generate using the app: infinite counters, count-up counters, count-down counters, and a task list. I'll start by explaining all the activities related to the process of an infinite counter.

Infinite Counters: Although a counter might sound simple, when it comes to implementing it with a user interface where you can generate it, interact with it, save it onto a database, and display it through a ListView, it has surprised me to be very tedious and hard work. To save time and space, I will only explain the process of generating an infinite counter, as the process is similar to creating the goal counters. However, I will still explain the process of creating the task list as it is quite different. I will start where the process of generating a counter begins, which is the main activity:

--------------------------

**1. Main Activity (`MainActivity.java`):**
   - This is the entry point of your app.
   - It initializes the database, retrieves data (Infinite Counters) from the database, and displays them in a ListView using a custom adapter.
   - Users can interact with the Infinite Counters by clicking on buttons to open different features.

**2. Creating an Infinite Counter (`ADD_COUNTER.java`):**
   - When the user clicks the "Add Counter" button, the `openaddcounter()` method is triggered, leading to the `ADD_COUNTER` activity.
   - In this activity, the user is presented with three options: Infinite Counter, Goal Counter, and Task.
   - If the user selects "Infinite Counter," it navigates to the `infincounter_setup` activity.

**3. Infinite Counter Setup (`infincounter_setup.java`):**
   - In this activity, the user can set up their Infinite Counter by providing a name for it.
   - When the user clicks the "Save" button, it checks if a name is provided. If not, it displays a toast message.
   - If a name is provided, it inserts the Infinite Counter's details (name and initial clicks, which start at 0) into the database (`database1`) using the `addinfincounter` method.
   - After saving, it returns to the main activity (`MainActivity.java`) where the updated list of Infinite Counters is displayed.

**4. Displaying Infinite Counters (`newListAdapter.java`):**
   - The `newListAdapter` class extends `ArrayAdapter` and is responsible for populating the ListView in the main activity.
   - It inflates a custom layout (`adapter_view.xml`) for each item in the ListView.
   - It retrieves the data for each Infinite Counter from the database and binds it to the appropriate views in the item layout.
   - Users can interact with each Infinite Counter item by clicking buttons:
     - "Ascend" button increases the current clicks by 1, updates the database, and refreshes the view.
     - "Descend" button decreases the current clicks by 1, updates the database, and refreshes the view.
     - Overflow button (`overflow_Button`) opens a popup menu with options to reset or delete the counter.

**5. Handling Counter Actions (`newListAdapter.java`):**
   - The `newListAdapter` class also handles actions like resetting and deleting counters.
   - Resetting a counter sets its current clicks to 0, updates the database, and refreshes the view.
   - Deleting a counter removes it from the database, removes the corresponding object from the list, and refreshes the view.

This process allows users to create, interact with, and manage Infinite Counters in your app. The data for these counters is stored in a SQLite database (`database1`) and displayed in a ListView using a custom adapter (`newListAdapter`). Users can increment, decrement, reset, or delete counters, and these actions are reflected both in the database and the user interface.

=============================
=============================

#### The following explains the process of creating and displaying tasks and subtasks:

**1. TaskList333 Activity (`TaskList333.java`):**
   - `TaskList333` is an activity that serves as a task list screen in your app.
   - It initializes various UI elements, including a ListView for displaying tasks and several Floating Action Buttons for navigation.
   - It retrieves task data from a database (`database3`) and uses a custom adapter (`TaskListAdapter`) to populate the ListView with tasks.
   - Users can navigate to other screens (Infinite Counter, Ascending Counter, Add Counter, Descending Counter) by clicking the corresponding Floating Action Buttons.

**2. TaskConstructor Class (`TaskConstructor.java`):**
   - `TaskConstructor` is a Java class that represents a task with attributes like ID, task name, due date, and completion status.
   - It provides constructors to create task objects and getter/setter methods for accessing and modifying task attributes.

**3. TaskListAdapter Class (`TaskListAdapter.java`):**
   - `TaskListAdapter` is an adapter class responsible for populating the task list view in `TaskList333` with task data.
   - It extends `ArrayAdapter` and inflates a custom layout for each task item.
   - It handles the display of task name, due date, completion status, and provides a checkbox to mark tasks as completed.
   - it also includes a recycler view which will display the subtasks from another subtasks table
   - It also includes a popup menu for options like deleting tasks.


**4. SubtaskConstructor Class (`SubtaskConstructor.java`):**
   - `SubtaskConstructor` is another Java class that represents subtasks associated with a main task.
   - It includes attributes like ID, main task ID, subtask name, and completion status.
   - Like `TaskConstructor`, it provides constructors and getter/setter methods for these attributes.

**5. SubtaskListAdapter Class (`SubtaskListAdapter.java`):**
   - `SubtaskListAdapter` is an adapter class for displaying subtasks within a RecyclerView.
   - It handles the display of subtask names, completion status, and strike-through effects for completed subtasks.
   - Users can mark subtasks as completed or not completed by interacting with checkboxes.

