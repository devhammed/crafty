package io.github.devhammed.crafty;

import java.util.List;

public class Function implements Callable {
    private final Stmt.Function declaration;

    Function(Stmt.Function declaration) {
        this.declaration = declaration;
    }

    @Override
    public int arity() {
        return declaration.params.size();
    }

    @Override
    public Object call(Interpreter interpreter, Environment environment,
            List<Object> arguments) {
        Environment scope = new Environment(environment);

        for (int i = 0; i < declaration.params.size(); i++) {
            scope.define(declaration.params.get(i).lexeme,
                    arguments.get(i));
        }

        interpreter.executeBlock(declaration.body, scope);

        return null;
    }

    @Override
    public String toString() {
        return "<fn " + declaration.name.lexeme + ">";
    }
}
