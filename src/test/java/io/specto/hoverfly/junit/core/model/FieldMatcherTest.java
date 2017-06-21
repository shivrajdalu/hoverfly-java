package io.specto.hoverfly.junit.core.model;


import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FieldMatcherTest {

    private FieldMatcher fieldMatcher;


    @Test
    public void shouldGetMatchPatternFromOneOfThePlainTextMatchers() throws Exception {
        fieldMatcher = FieldMatcher.exactlyMatches("/api/v1");

        assertThat(fieldMatcher.getMatchPattern()).isEqualTo("/api/v1");
    }


    @Test
    public void shouldThrowExceptionWhenNoneOfThePlainTextMatcherIsSet() throws Exception {
        fieldMatcher = new FieldMatcher.Builder().build();

        assertThatThrownBy(() -> fieldMatcher.getMatchPattern())
                .isInstanceOf(IllegalStateException.class);
    }
}