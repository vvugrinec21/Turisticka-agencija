package edu.unizg.foi.uzdiz.vvugrinec21.app.visitor.element;

import edu.unizg.foi.uzdiz.vvugrinec21.app.visitor.PretrazivacVisitor;

public interface PretraziviElement {
    void accept(PretrazivacVisitor visitor);
}
