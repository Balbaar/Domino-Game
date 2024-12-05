# Todo List

---

### Pseudo Code

An ArrayList of all owned items will be checked after rolling. Based on items events in a second round of effects will be added and the score will be calculated. 

```java
import java.awt.image.BufferedImage;

//A class for base item NOT DISPLAYED JUST INFORMATION
private class Item {
  BufferedImage image;
  private int priority; //based on priority, the item will take effect before others
  /*
   * Priority 1: Happens before all the dominos are counted and the score is calculated
   * These only apply on single domino. Example: add some value to it based on conditions of item */
  /*
   * Priority 2: Happens after all the dominos are counted and the second round of effects and dominos new values are calculated based on priority 1 items
   * These items apply effects on multiple dominos. Example: Modify the neighboring dominos based on the current domino.*/
  /*
   * Priority 3: Idk, maybe some other effects that are not related to the dominos. */
   
  
}
```

### Todo
- [X] Make shake effect more accessible in domino class
- [X] Rename more domino files
- [X] Implement sidepanel object
- [X] Create booleans for domino such as 
    - [X] sum is even
    - [X] has a zero
    - [X] is a double
- [X] Create a score object for sidepanel
- [ ] Design a better roll dominos button
- [ ] Create store panel object for sidepanel that has a list 4 random items
- [ ] Think about how the events will be handled and what counts as a valid event
- [ ] Rework/Add event types. Example: Domino is lifted up, Domino is lifted down, Domino is shaking, Domino is removed, etc.
- [ ] Based on the event type, apply different effects to the domino.

### Bugs
- [ ] 