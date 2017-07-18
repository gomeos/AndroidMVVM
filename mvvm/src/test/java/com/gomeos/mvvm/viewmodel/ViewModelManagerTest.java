package com.gomeos.mvvm.viewmodel;

import android.os.Bundle;

import com.gomeos.mvvm.ReflectUtil;
import com.gomeos.mvvm.BuildConfig;
import com.gomeos.mvvm.MyRunner;
import com.gomeos.mvvm.view.ui.BaseActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.robolectric.annotation.Config;

import java.lang.reflect.Field;
import java.util.List;

import static android.os.Build.VERSION_CODES.LOLLIPOP;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by wwish on 16/8/29.
 */
@RunWith(MyRunner.class)
@Config(constants = BuildConfig.class, sdk = LOLLIPOP)
public class ViewModelManagerTest {

    public static class TestLifecycleViewModel extends LifecycleViewModel {

    }

    TestLifecycleViewModel testLifecycleViewModel;

    BaseActivity baseActivity = Mockito.mock(BaseActivity.class);

    ViewModelManager viewModelManager;
    Bundle bundle=new Bundle();
    @Before
    public void setUp() throws Exception {
        testLifecycleViewModel = new TestLifecycleViewModel();
        testLifecycleViewModel.setContext(baseActivity);

        viewModelManager = new ViewModelManager();
    }

    @After
    public void tearDown() throws Exception {


    }

    @Test
    public void testAddViewModel() throws Exception {
        Field field = ViewModelManager.class.getDeclaredField("lifecycleViewModelList");
        field.setAccessible(true);
        List<LifecycleViewModel> list = (List<LifecycleViewModel>) field.get(viewModelManager);
        assertNotNull(list);

        list = Mockito.spy(list);
        field.set(viewModelManager, list);

        TestLifecycleViewModel testLifecycleViewModelSpy = new TestLifecycleViewModel();

        viewModelManager.addViewModel(testLifecycleViewModelSpy);
        Mockito.verify(list).add(testLifecycleViewModelSpy);
    }

    @Test
    public void testRemoveViewModel() throws Exception {
        Field field = ViewModelManager.class.getDeclaredField("lifecycleViewModelList");
        field.setAccessible(true);
        List<LifecycleViewModel> list = (List<LifecycleViewModel>) field.get(viewModelManager);
        assertNotNull(list);

        list = Mockito.spy(list);
        field.set(viewModelManager, list);

        TestLifecycleViewModel testLifecycleViewModelSpy = new TestLifecycleViewModel();

        viewModelManager.addViewModel(testLifecycleViewModelSpy);
        viewModelManager.removeViewModel(testLifecycleViewModelSpy);
        Mockito.verify(list).remove(testLifecycleViewModelSpy);
    }

    @Test
    public void testSetUserVisibleHint() throws Exception {
        TestLifecycleViewModel testLifecycleViewModelSpy = Mockito.spy(testLifecycleViewModel);
        ReflectUtil.invoke(testLifecycleViewModelSpy, "init");
        viewModelManager.addViewModel(testLifecycleViewModelSpy);
        ArgumentCaptor<Boolean> argument = ArgumentCaptor.forClass(Boolean.class);
        viewModelManager.setUserVisibleHint(false);
        Mockito.verify(testLifecycleViewModelSpy).setUserVisibleHint(argument.capture());
        assertEquals(false, argument.getValue());
    }

    @Test
    public void testOnWindowFocusChanged() throws Exception {
        TestLifecycleViewModel testLifecycleViewModelSpy = Mockito.spy(testLifecycleViewModel);
        ReflectUtil.invoke(testLifecycleViewModelSpy, "init");
        viewModelManager.addViewModel(testLifecycleViewModelSpy);
        ArgumentCaptor<Boolean> argument = ArgumentCaptor.forClass(Boolean.class);
        viewModelManager.onWindowFocusChanged(false);
        Mockito.verify(testLifecycleViewModelSpy).onWindowFocusChanged(argument.capture());
        assertEquals(false, argument.getValue());
    }

    @Test
    public void testCreate() throws Exception {
        TestLifecycleViewModel testLifecycleViewModelSpy = Mockito.spy(testLifecycleViewModel);
        ReflectUtil.invoke(testLifecycleViewModelSpy, "init");
        viewModelManager.addViewModel(testLifecycleViewModelSpy);
        viewModelManager.create();
        Field savedInstanceStateField = ViewModelManager.class.getDeclaredField("savedInstanceState");
        savedInstanceStateField.setAccessible(true);
        Bundle savedInstanceStateBundle = (Bundle) savedInstanceStateField.get(viewModelManager);
        Mockito.verify(testLifecycleViewModelSpy).create(savedInstanceStateBundle);
    }

    @Test
    public void testStart() throws Exception {
        TestLifecycleViewModel testLifecycleViewModelSpy = Mockito.spy(testLifecycleViewModel);
        ReflectUtil.invoke(testLifecycleViewModelSpy, "init");
//        Field field = ViewModelManager.class.getDeclaredField("lifecycleState");
//        field.setAccessible(true);
//        LifecycleState lifecycleState=(LifecycleState)field.get(viewModelManager);
//        lifecycleState=LifecycleState.Created;
        viewModelManager.addViewModel(testLifecycleViewModelSpy);
        viewModelManager.create();
        viewModelManager.start();
        Mockito.verify(testLifecycleViewModelSpy).start();
    }

    @Test
    public void testResume() throws Exception {
        TestLifecycleViewModel testLifecycleViewModelSpy = Mockito.spy(testLifecycleViewModel);
        ReflectUtil.invoke(testLifecycleViewModelSpy, "init");
        viewModelManager.addViewModel(testLifecycleViewModelSpy);
        viewModelManager.create();
        viewModelManager.start();
        viewModelManager.resume();
        Mockito.verify(testLifecycleViewModelSpy).resume();

    }

    @Test
    public void testPause() throws Exception {
        TestLifecycleViewModel testLifecycleViewModelSpy = Mockito.spy(testLifecycleViewModel);
        ReflectUtil.invoke(testLifecycleViewModelSpy, "init");
        viewModelManager.addViewModel(testLifecycleViewModelSpy);
        viewModelManager.create();
        viewModelManager.start();
        viewModelManager.resume();
        viewModelManager.pause();
        Mockito.verify(testLifecycleViewModelSpy).pause();

    }

    @Test
    public void testStop() throws Exception {
        TestLifecycleViewModel testLifecycleViewModelSpy = Mockito.spy(testLifecycleViewModel);
        ReflectUtil.invoke(testLifecycleViewModelSpy, "init");
        viewModelManager.addViewModel(testLifecycleViewModelSpy);
        viewModelManager.create();
        viewModelManager.start();
        viewModelManager.resume();
        viewModelManager.pause();
        viewModelManager.stop();
        Mockito.verify(testLifecycleViewModelSpy).stop();

    }


    @Test
    public void testSaveInstanceState() throws Exception {
        final Bundle bundle = new Bundle();
        TestLifecycleViewModel testLifecycleViewModelSpy = Mockito.spy(testLifecycleViewModel);
        viewModelManager.addViewModel(testLifecycleViewModelSpy);
        viewModelManager.saveInstanceState(bundle);
//        Visitor<LifecycleViewModel> onSaveInstanceState =
//                new Visitor<LifecycleViewModel>() {
//                    @Override
//                    public void visit(LifecycleViewModel data) {
//                        Bundle bundle1 = new Bundle();
//                        data.onSaveInstanceState(bundle1);
//                        bundle.putBundle(data.getTag(), bundle1);
//                    }
//                };
//        Mockito.verify(testLifecycleViewModelSpy).accept(onSaveInstanceState);
    }

    @Test
    public void testRestoreInstanceState() throws Exception {

        Bundle bundle = new Bundle();
        TestLifecycleViewModel testLifecycleViewModelSpy = Mockito.spy(testLifecycleViewModel);
        viewModelManager.addViewModel(testLifecycleViewModelSpy);
        viewModelManager.restoreInstanceState(bundle);
//        Mockito.verify(testLifecycleViewModelSpy).onRestoreInstanceState(bundle);
    }
}
