package com.simmons.advent.days.y2023;

import com.simmons.advent.days.model.AbstractDay;
import com.simmons.advent.utils.InputUtils;
import java.awt.*;
import java.util.*;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

public class Day20_2023 extends AbstractDay {

  public static final Module BUTTON = new Module("button");
  public static final String BROADCASTER = "broadcaster";
  public static final String TARGET = "rx";

  public Day20_2023() {
    super(2023, 20);
  }

  public String solvePartOne(String input) {
    List<String> lines = InputUtils.getInputAsList(input);
    Map<String, Module> modules = buildModulesFromInput(lines);
    Deque<Broadcast> queue = new ArrayDeque<>();
    for (int i = 0; i < 1000; i++) {
      queue.add(new Broadcast(BUTTON, modules.get(BROADCASTER), Pulse.LOW));
      while (queue.size() > 0) {
        Broadcast broadcast = queue.poll();
        broadcast.receiver.receive(broadcast, queue);
      }
    }

    long answer = Module.counts.get(Pulse.LOW) * Module.counts.get(Pulse.HIGH);
    return String.valueOf(answer);
  }

  public String solvePartTwo(String input) {
    List<String> lines = InputUtils.getInputAsList(input);
    Map<String, Module> modules = buildModulesFromInput(lines);
    if (modules.containsKey(TARGET)) {
      Module targetModule = modules.get(TARGET);
      Map<String, Long> cycleCounts = new HashMap<>();
      targetModule.senders.stream()
          .forEach(m -> m.senders.forEach(s -> cycleCounts.put(s.name, 0L)));
      Deque<Broadcast> queue = new ArrayDeque<>();
      long presses = 1L;
      while (cycleCounts.values().stream().anyMatch(c -> c == 0)) {
        queue.add(new Broadcast(BUTTON, modules.get(BROADCASTER), Pulse.LOW));
        while (queue.size() > 0) {
          Broadcast broadcast = queue.poll();
          String senderName = broadcast.sender.name;
          if (cycleCounts.containsKey(senderName)
              && cycleCounts.get(senderName) == 0
              && broadcast.pulse == Pulse.HIGH) {
            cycleCounts.put(senderName, presses);
          }
          broadcast.receiver.receive(broadcast, queue);
        }
        presses++;
      }

      long answer = cycleCounts.values().stream().reduce(1L, (a, b) -> a * b);
      return String.valueOf(answer);
    }
    return "";
  }

  private Map<String, Module> buildModulesFromInput(List<String> lines) {
    Map<String, Module> modules = new HashMap<>();
    for (String line : lines) {
      int arrow = line.indexOf("->");

      String senderString = line.substring(0, arrow - 1);
      boolean flip = senderString.startsWith("%");
      boolean conj = senderString.startsWith("&");
      if (flip || conj) {
        senderString = senderString.substring(1);
      }
      String receiverString = line.substring(arrow + 3);
      Module sender = modules.computeIfAbsent(senderString, k -> new Module(k));
      if (flip) {
        sender.setModuleType(ModuleType.FLIP_FLOP);
      }
      if (conj) {
        sender.setModuleType(ModuleType.CONJUNCTION);
      }
      if (senderString.equals(BROADCASTER)) {
        sender.setModuleType(ModuleType.BROADCASTER);
      }

      for (String rec : receiverString.split(", ")) {
        Module receiver = modules.computeIfAbsent(rec, k -> new Module(k));
        sender.addReceiver(receiver);
        receiver.addSender(sender);
      }
    }

    for (Module module : modules.values()) {
      if (module.getType() == ModuleType.CONJUNCTION) {
        module.setPulseMap();
      }
    }
    return modules;
  }
}

@Data
@EqualsAndHashCode
class Module {
  public static final Map<Pulse, Long> counts = new HashMap<>();
  String name;
  List<Module> receivers;
  List<Module> senders;
  Pulse current;
  ModuleType type = ModuleType.OUTPUT;
  public boolean on;

  public boolean canSend = true;
  Map<String, Pulse> pulseMap;

  public Module(String name) {
    this.name = name;
    current = Pulse.LOW;
    receivers = new ArrayList<>();
    senders = new ArrayList<>();
    pulseMap = new HashMap<>();
  }

  public void addReceiver(Module other) {
    receivers.add(other);
  }

  public void addSender(Module other) {
    senders.add(other);
  }

  public void setPulseMap() {
    for (Module module : senders) {
      pulseMap.put(module.name, Pulse.LOW);
    }
  }

  public void setModuleType(ModuleType type) {
    this.type = type;
  }

  public void receive(Broadcast broadcast, Deque<Broadcast> queue) {
    counts.put(broadcast.pulse, counts.getOrDefault(broadcast.pulse, 0L) + 1);

    switch (type) {
      case BROADCASTER:
        current = broadcast.pulse;
        for (Module module : receivers) {
          queue.add(new Broadcast(this, module, current));
        }
        break;
      case FLIP_FLOP:
        if (broadcast.pulse == Pulse.LOW) {
          on = !on;
          canSend = true;
          current = on ? Pulse.HIGH : Pulse.LOW;
          for (Module module : receivers) {
            queue.add(new Broadcast(this, module, current));
          }
        }
        break;
      case CONJUNCTION:
        pulseMap.put(broadcast.sender.name, broadcast.pulse);
        current =
            pulseMap.values().stream().allMatch(p -> p == Pulse.HIGH) ? Pulse.LOW : Pulse.HIGH;
        for (Module module : receivers) {
          queue.add(new Broadcast(this, module, current));
        }
        break;
      case OUTPUT:
      default:
        break;
    }
  }

  public String toString() {
    return String.format("(%s %s %s %s)", name, type, current, on);
  }
}

@Data
@AllArgsConstructor
@EqualsAndHashCode
class Broadcast {
  Module sender;
  Module receiver;
  Pulse pulse;
}

enum Pulse {
  LOW,
  HIGH;
}

enum ModuleType {
  BROADCASTER,
  FLIP_FLOP,
  CONJUNCTION,
  OUTPUT
}
