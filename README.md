#Feature Switches

Feature Switches is a utility library that enables feature switch management in your client code to drive behaviour and logic using HOCON configuration.

There are two types of feature switches that can be created.
1. Boolean Switches
2. DateTime Switches

##Usage

A context is defined as an object in HOCON which contains your feature switches.

###Default Context

The default context is known as `switches.default` and can be defined in your HOCON .conf files as below:
``` HOCON
switches.default {
    switchA = true
    switchB = false
    multilevel {
        switchC = "2020-01-01T00:00:00.000Z"
        switchD = "false"
    }
}
```

You can then reference these feature switches in your code base as below:

```java
public class Main {
    public static void main(String[] args) {
        Switch s = FeatureSwitches.getInstance().getSwitch("switchB"); //true
        s.turnOff();
        s.reset();

        Switch c = FeatureSwitches.getInstance().getSwitch("multilevel.switchC"); //true
    }
}
```

###Custom Context

You can also logically separate your feature switches into logical parts by creating your own contexts:
```HOCON

customSwitchesContext {
    switchE = true
}
```

Which can be referenced in your code as follows:
```java
public class Main {
    public static void main(String[] args) {
        Switch s = FeatureSwitches.getInstance().getSwitch("customSwitchesContext" , "switchE"); //true
    }
}
```