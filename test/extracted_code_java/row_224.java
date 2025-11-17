sharp
using System;
using System.Collections.Generic;

public class EventManager<TEventArgs> where TEventArgs : EventArgs
{
    private readonly List<EventHandler<TEventArgs>> _handlers = new List<EventHandler<TEventArgs>>();

    public void AddHandler(EventHandler<TEventArgs> handler)
    {
        if (handler == null)
            throw new ArgumentNullException(nameof(handler));

        // Check for duplicates
        if (!_handlers.Contains(handler))
        {
            _handlers.Add(handler);
        }
    }

    public void RaiseEvent(object sender, TEventArgs e)
    {
        foreach (var handler in _handlers)
        {
            handler(sender, e);
        }
    }
}

public class Program
{
    public static void Main()
    {
        var eventManager = new EventManager<CustomEventArgs>();

        // Register the same handler multiple times
        CustomEventHandler handler1 = (s, e) => Console.WriteLine("Handler 1 called");
        CustomEventHandler handler2 = (s, e) => Console.WriteLine("Handler 2 called");

        eventManager.AddHandler(handler1);
        eventManager.AddHandler(handler1); // Duplicate registration, should not add another instance
        eventManager.AddHandler(handler2);

        // Raise the event
        eventManager.RaiseEvent(null, new CustomEventArgs());
    }
}

public class CustomEventArgs : EventArgs
{
    // Custom event arguments if needed
}

public delegate void CustomEventHandler(object sender, CustomEventArgs e);