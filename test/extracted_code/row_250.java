script
class EventEmitter {
  constructor() {
    this._events = {}; // Object to hold events and their handlers
  }

  /**
   * Register a new event handler for an event type.
   * @param {string} eventType - The type of the event.
   * @param {Function} handler - The function to be called when the event occurs.
   */
  on(eventType, handler) {
    if (!this._events[eventType]) {
      this._events[eventType] = []; // Initialize an array for this event type if it doesn't exist
    }
    this._events[eventType].push(handler); // Add the handler to the list of handlers for this event
  }

  /**
   * Trigger all handlers for a given event.
   * @param {string} eventType - The type of the event to trigger.
   * @param {...any} args - Arguments to pass to the event handlers.
   */
  emit(eventType, ...args) {
    if (!this._events[eventType]) {
      console.warn(`No handlers registered for event: ${eventType}`);
      return;
    }
    this._events[eventType].forEach((handler) => handler(...args)); // Call each handler with the provided arguments
  }
}

// Example usage:
const emitter = new EventEmitter();

emitter.on('click', (data) => console.log('Click event 1:', data));
emitter.on('click', (data) => console.log('Click event 2:', data));

emitter.emit('click', { message: 'Hello, world!' });
// Outputs:
// Click event 1: { message: 'Hello, world!' }
// Click event 2: { message: 'Hello, world!' }