package io.github.devhammed.crafty;

public class ReturnStatement extends RuntimeException {
    final Object value;

    ReturnStatement(Object value) {
        super(null, null, false, false);
        this.value = value;
    }
}
