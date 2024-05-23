package org.example.demo;

import javafx.animation.SequentialTransition;
import javafx.animation.Transition;

public interface Model {

    SequentialTransition getPushAnimation();

    SequentialTransition getPopAnimation();

    SequentialTransition getPeekAnimation();
}
