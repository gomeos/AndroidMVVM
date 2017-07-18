package com.gomeos.mvvm.model;

import android.content.Context;

import com.gomeos.mvvm.BuildConfig;
import com.gomeos.mvvm.MyRunner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertEquals;

/**
 * Created by wwish on 16/8/26.
 */
@RunWith(MyRunner.class)
@Config(constants = BuildConfig.class)
public class UseCaseTest {
    static class TestUseCase extends UseCase {

        @Override
        protected void onOpen() {
        }

        @Override
        protected void onClose() {

        }
    }

    public class TestUseCaseHolder implements UseCaseHolder {

        @Override
        public String getUseCaseHolderId() {
            return null;
        }
    }


    Context context;
    final String preferenceFile = "test.xml";
    TestUseCase testUseCase;

    @Before
    public void setUp() throws Exception {
        context = RuntimeEnvironment.application.getBaseContext();
        testUseCase = new TestUseCase();
        testUseCase.setContext(context);
    }

    @After
    public void tearDown() throws Exception {
        context=null;
        testUseCase=null;
    }

    @Test
    public void testOpen() {
        TestUseCase testUseCaseSpy = Mockito.spy(testUseCase);
        testUseCaseSpy.open();
        Mockito.verify(testUseCaseSpy).onOpen();

    }

    @Test
    public void testClose() {
        TestUseCase testUseCaseSpy = Mockito.spy(testUseCase);
        testUseCaseSpy.open();
        testUseCaseSpy.close();
        Mockito.verify(testUseCaseSpy).onClose();
    }


    @Test
    public void testPreferencePutInt() throws Exception {
        testUseCase.preferencePutInt(preferenceFile, "putIntKey", 1);
        int i = testUseCase.preferenceGetInt(preferenceFile, "putIntKey");
        assertEquals(1, i);
        testUseCase.setContext(RuntimeEnvironment.application);
        testUseCase.preferencePutInt(preferenceFile, "putIntKey", 1);
    }

    @Test
    public void testPreferencePutLong() throws Exception {
        testUseCase.preferencePutLong(preferenceFile, "putLongKey", 10L);
        long l = testUseCase.preferenceGetLong(preferenceFile, "putLongKey");
        assertEquals(10l, l);

    }

    @Test
    public void testPreferencePutFloat() throws Exception {
        testUseCase.preferencePutFloat(preferenceFile, "putFloatKey", 1.0f);
        float f = testUseCase.preferenceGetFloat(preferenceFile, "putFloatKey");
        assertEquals(1.0, f, 0.0000000001f);
    }

    @Test
    public void testPreferencePutString() throws Exception {
        testUseCase.preferencePutString(preferenceFile, "putStringKey", "stringtest");
        String string = testUseCase.preferenceGetString(preferenceFile, "putStringKey");
        assertEquals("stringtest", string);
    }

    @Test
    public void testUseCaseHolderExists() throws Exception {
        UseCaseHolder useCaseHolder=new TestUseCaseHolder();
//        testUseCase.useCaseHolderExists(useCaseHolder);

    }
}
