package com.starter.conditions;

import org.springframework.boot.autoconfigure.condition.AllNestedConditions;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

public class OnProductionConditions extends AllNestedConditions
{

    public OnProductionConditions()
    {
        super(ConfigurationPhase.REGISTER_BEAN);
    }

    @ConditionalOnProperty(name = "info.previous-versions")
    public static class OnPreviousVersions{}

    @ConditionalOnProperty(name = "info.enabled", havingValue = "true", matchIfMissing = true)
    public static class OnInfoEnabled{}

}
