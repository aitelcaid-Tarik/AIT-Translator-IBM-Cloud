package com.android.watson.translator;

import android.content.Context;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class UnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Mock
    Context mockContext;

    @Test
    public void translate_isCorrect() {

        // Given a mocked Context injected into the object under test...
        when(mockContext.getString(R.string.api_key))
                .thenReturn("HR2S1fqP8YQp0xo1Nx2363nKAm-L1dgj2ndkL_nopBl-");

        // Given a mocked Context injected into the object under test...
        when(mockContext.getString(R.string.url))
                .thenReturn("https://api.eu-gb.language-translator.watson.cloud.ibm.com/instances/13c5cea2-f786-4077-9daa-cbe165f2abec");

        assertEquals("Bonjour", new MainActivity().translate(mockContext,"Hello", "en", "fr"));

    }

}