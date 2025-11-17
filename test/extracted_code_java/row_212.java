bash
pip install schedule
import schedule
   from datetime import datetime
   from collections import defaultdict
class TaskRegistry:
       def __init__(self):
           self.tasks = defaultdict(list)  # Dictionary to map task names to their schedules

       def register_task(self, name, time, action):
           """Register or update a task."""
           if name in self.tasks:
               for schedule in self.tasks[name]:
                   schedule.clear()  # Clear existing schedules for the same task
           else:
               print(f"New task '{name}' registered.")

           def wrapped_action():
               """Wrapper to pass arguments to actions."""
               action(name)  # Execute the actual task logic with name as argument

           schedule.every().day.at(time).do(wrapped_action)
           self.tasks[name].append(schedule.get_jobs()[0])  # Append the scheduled job to keep it alive
def task_action(task_name):
    print(f"{datetime.now()} | Task '{task_name}' executed.")

# Initialize the registry
registry = TaskRegistry()

# Register a new task
registry.register_task("morning_routine", "08:00", task_action)

# Register another task, this will replace the previous one since they share the same name
registry.register_task("morning_routine", "10:00", task_action)

# Function to keep the script running and trigger tasks
def run_scheduled_tasks():
    while True:
        schedule.run_pending()
        time.sleep(1)

if __name__ == "__main__":
    run_scheduled_tasks()