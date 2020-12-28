package com.starter.conditions;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import javax.swing.*;

public class OnProductionConditional implements Condition
{

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata)
    {
        return JOptionPane.showConfirmDialog(null, "Is it production?") == 0;
    }
}
