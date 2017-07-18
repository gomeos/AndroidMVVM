package com.gomeos.mvvm.model;

import com.gomeos.mvvm.BuildConfig;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static android.os.Build.VERSION_CODES.LOLLIPOP;
import static junit.framework.Assert.assertNotNull;

/**
 * Created by wwish on 16/8/29.
 */
//@RunWith(MyRunner.class)
//@Config(constants = BuildConfig.class)
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = LOLLIPOP)
public class UseCaseManagerTest {
    public static class TestUseCase extends UseCase {
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

    UseCaseManager useCaseManager;

    @Before
    public void setUp() throws Exception {
        useCaseManager = new UseCaseManager(RuntimeEnvironment.application);

    }

    @After
    public void tearDown() throws Exception {
        useCaseManager=null;

    }

    @Test
    public void testRegister() throws Exception {

        useCaseManager.register(TestUseCase.class);
//        assertNotNull(TestUseCase.class);

        try{
            useCaseManager.register(TestUseCase.class);
        }catch (IllegalArgumentException e){

        }
    }

    @Test
    public void testObtainUseCase() throws Exception {
        useCaseManager.register(TestUseCase.class);
        UseCaseHolder useCaseHolder=new TestUseCaseHolder();
        TestUseCase useCaseTest = useCaseManager.obtainUseCase(TestUseCase.class,useCaseHolder);
        assertNotNull(useCaseTest);
    }

    @Test
    public void testOnUseCaseHolderDestroy() throws Exception {
        UseCaseHolder useCaseHolder=new TestUseCaseHolder();
        useCaseManager.register(TestUseCase.class);
        TestUseCase useCaseTest = useCaseManager.obtainUseCase(TestUseCase.class,useCaseHolder);
        useCaseTest.close();
    }
}
