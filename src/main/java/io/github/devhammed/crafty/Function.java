package io.github.devhammed.crafty;

import java.util.List;

public class Function implements Callable {
    private final Environment closure;
    private final Stmt.Function declaration;

    Function(Stmt.Function declaration, Environment closure) {
        this.closure = closure;
        this.declaration = declaration;
    }

    @Override
    public int arity() {
        return declaration.params.size();
    }

    @Override
    public Object call(Interpreter interpreter, List<Object> arguments) {
        Environment scope = new Environment(closure);

        for (int i = 0; i < declaration.params.size(); i++) {
            scope.define(declaration.params.get(i).lexeme,
                    arguments.get(i));
        }

        try {
            interpreter.executeBlock(declaration.body, scope);
        } catch (ReturnStatement returnStmt) {
            return returnStmt.value;
        }

        return null;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("fun ");
        builder.append(declaration.name.lexeme);
        builder.append("(");

        for (int i = 0, len = declaration.params.size(); i < len; i++) {
            builder.append(declaration.params.get(i).lexeme);

            if (i != len - 1) {
                builder.append(", ");
            }
        }

        builder.append(") { [code] }");

        return builder.toString();
    }
}
