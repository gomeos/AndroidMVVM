package com.gomeos.mvvm.view.ui;

import com.gomeos.mvvm.MyRunner;
import com.gomeos.mvvm.BuildConfig;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

import static android.os.Build.VERSION_CODES.LOLLIPOP;

/**
 * Created by wwish on 16/9/12.
 */
@RunWith(MyRunner.class)
@Config(constants = BuildConfig.class, sdk = LOLLIPOP)
public class BaseActivityTest {


    BaseActivity mBaseActivity;

//    public class TestActivityResultListener implements BaseActivity.ActivityResultListener {
//        @Override
//        public void onActivityResult(int requestCode, int resultCode, Intent intent) {
//
//        }
//    }

    @Before
    public void setUp() throws Exception {
        mBaseActivity=new BaseActivity();
    }

    @After
    public void tearDown() throws Exception {

    }

    /**
     * Activity生命周期测试
     */
    @Test
    public void testLifecycle() throws Exception{

//        ActivityController controller = Robolectric.buildActivity(BaseActivity.class).create().start();
//
//        ActivityController<BaseActivity> activityController = Robolectric.buildActivity(BaseActivity.class).create();
//        Activity activity = activityController.get();
//
//        Field field = BaseActivity.class.getDeclaredField("runState");
//        field.setAccessible(true);
//        RunState runState=(RunState)field.get(activity);
//
//        assertEquals(RunState.Created, runState);
//
//        activityController.start();
//        runState=(RunState)field.get(activity);
//        assertEquals(RunState.Started, runState);
//
//        activityController.resume();
//        runState=(RunState)field.get(activity);
//        assertEquals(RunState.Resumed, runState);
//
//        activityController.pause();
//        runState=(RunState)field.get(activity);
//        assertEquals(RunState.Paused, runState);
//
//        activityController.stop();
//        runState=(RunState)field.get(activity);
//        assertEquals(RunState.Stopped, runState);
//
//        activityController.destroy();
//        runState=(RunState)field.get(activity);
//        assertEquals(RunState.Destroyed, runState);
    }

    @Test
    public void testOnAttachFragment() throws Exception {

    }

//    @Test
//    public void testRegisterActivityResultListener() throws Exception {
////        BaseActivity BaseActivity= Mockito.spy(mBaseActivity);
//        TestActivityResultListener activityResultListener=new TestActivityResultListener();
//        mBaseActivity.registerActivityResultReceiver(1,activityResultListener);
//        Field field = BaseActivity.class.getDeclaredField("activityResultListeners");
//        field.setAccessible(true);
////        field.set(mBaseActivity, new SparseArray<BaseActivity.ActivityResultListener>());
//        SparseArray<TestActivityResultListener> activityResultListeners =(SparseArray<TestActivityResultListener>)field.get(mBaseActivity);
//        assertEquals(activityResultListener,activityResultListeners.get(1));
//
//    }

    @Test
    public void testOnActivityResult() throws Exception {

    }

    @Test
    public void testOnWindowFocusChanged() throws Exception {
//        Field field = BaseActivity.class.getDeclaredField("isHasFocus");
//        field.setAccessible(true);
//        boolean isHasFocus=(boolean)field.get(mBaseActivity);
//
////        Field field1 = BaseActivity.class.getDeclaredField("viewModelManager");
////        field1.setAccessible(true);
////        ViewModelManager vm=(ViewModelManager)field1.get(mBaseActivity);
////        field1.set(mBaseActivity,vm);
//        System.out.println(isHasFocus);
//        assertEquals(isHasFocus,false);
//        ReflectUtil.invoke(mBaseActivity,"init",new Class []{Bundle.class},new Object[]{null});
//        mBaseActivity.onWindowFocusChanged(true);
//        System.out.println(isHasFocus);
//        assertEquals(true,isHasFocus);
    }

    @Test
    public void testOnCreate() throws Exception {

    }

    @Test
    public void testInit() throws Exception {
//        ReflectUtil.invoke(mBaseActivity,"init",new Class []{Bundle.class},new Object[]{null});
//        Field field = BaseActivity.class.getDeclaredField("runState");
//        field.setAccessible(true);
//        RunState runState=(RunState)field.get(mBaseActivity);
//
//        assertEquals(RunState.Created,runState);
    }

    @Test
    public void testOnStart() throws Exception {

    }

    @Test
    public void testOnStop() throws Exception {

    }

    @Test
    public void testOnSaveInstanceState() throws Exception {

    }

    @Test
    public void testOnRestoreInstanceState() throws Exception {

    }

    @Test
    public void testOnDestroy() throws Exception {

    }

    @Test
    public void testOnResume() throws Exception {

    }

    @Test
    public void testOnPause() throws Exception {

    }

    @Test
    public void testGetViewModelManager() throws Exception {

    }

    @Test
    public void testGetUseCaseHolderId() throws Exception {

    }

    @Test
    public void testAddViewModel() throws Exception {
//        LifecycleViewModel lifecycleViewModel=new LifecycleViewModel();
//        ReflectUtil.invoke(mBaseActivity,"init");
//        mBaseActivity.addViewModel(lifecycleViewModel);
//        Mockito.verify(mBaseActivity).getViewModelManager().addViewModel(lifecycleViewModel);
    }

    @Test
    public void testRemoveViewModel() throws Exception {

    }

    @Test
    public void testShowLoadingDialog() throws Exception {

    }

    @Test
    public void testShowLoadingDialog1() throws Exception {

    }

    @Test
    public void testDismissLoadingDialog() throws Exception {

    }
}