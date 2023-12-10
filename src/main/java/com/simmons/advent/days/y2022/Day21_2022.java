package com.simmons.advent.days.y2022;

import com.simmons.advent.days.model.AbstractDay;
import com.simmons.advent.utils.InputUtils;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class Day21_2022 extends AbstractDay {

  public static final Pattern NUMBER_PATTERN = Pattern.compile("-?\\d+");
  public static final Pattern NAME_PATTERN = Pattern.compile("[a-z]{4}");
  public static final String ROOT = "root";

  public Day21_2022() {
    super(2022, 21);
  }

  public String solvePartOne(String input) {
    List<MonkeyEquation> equations =
        InputUtils.getInputAsList(input).stream()
            .map(MonkeyEquation::new)
            .collect(Collectors.toList());
    Map<String, Long> values = new HashMap<>();
    Map<Expression, String> expressions = new HashMap<>();
    Deque<Expression> toEvaluate = new ArrayDeque<>();
    buildExpressionsFromEquations(equations, values, expressions, toEvaluate);

    while (!values.containsKey(ROOT)) {
      Expression expression = toEvaluate.poll();
      for (String var : expression.getVariableNames()) {
        if (values.containsKey(var)) {
          expression.setVariable(var, values.get(var));
        }
      }
      if (expression.validate().isValid()) {
        values.put(expressions.get(expression), (long) expression.evaluate());
      } else {
        toEvaluate.addLast(expression);
      }
    }
    return String.valueOf(values.get(ROOT));
  }

  private void buildExpressionsFromEquations(
      List<MonkeyEquation> equations,
      Map<String, Long> values,
      Map<Expression, String> expressions,
      Deque<Expression> toEvaluate) {
    for (MonkeyEquation equation : equations) {
      if (NUMBER_PATTERN.matcher(equation.equation).matches()) {
        values.put(equation.name, Long.parseLong(equation.equation));
      } else {
        ExpressionBuilder builder = new ExpressionBuilder(equation.equation);
        Matcher matcher = NAME_PATTERN.matcher(equation.equation);
        while (matcher.find()) {
          builder.variable(matcher.group());
        }
        Expression expression = builder.build();
        expressions.put(expression, equation.name);
        toEvaluate.addLast(expression);
      }
    }
  }

  public String solvePartTwo(String input) {
    return null;
  }

  class MonkeyEquation {
    String name;
    String equation;

    public MonkeyEquation(String line) {
      String[] split = line.split("\\s", 2);
      this.name = split[0].substring(0, split[0].length() - 1);
      this.equation = split[1];
    }
  }
}
