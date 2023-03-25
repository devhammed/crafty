package io.github.devhammed.crafty;

import java.util.List;

public interface Callable {
    int arity();

    Object call(Interpreter interpreter, Environment environment, List<Object> arguments);
}
