package com.gomeos.mvvm.view.ui;


import android.app.Fragment;

import com.gomeos.mvvm.MyRunner;
import com.gomeos.mvvm.BuildConfig;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.annotation.Config;
import org.robolectric.util.FragmentController;

/**
 * Created by wwish on 16/9/12.
 */
@RunWith(MyRunner.class)
@Config(constants = BuildConfig.class)
public class BaseFragmentTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    /**
     * fragment生命周期测试
     */
    @Test
    public void testLifecycle() throws Exception{

        FragmentController<Fragment> fragmentController = Robolectric.buildFragment(Fragment.class).create();
        Fragment fragment = fragmentController.get();

    }

    @Test
    public void shouldNotNull() throws Exception
    {
//        BaseFragment fragment = new BaseFragment();
//        startFragment( fragment );
//        assertNotNull( fragment );
    }

    @Test
    public void testGetViewModelManager() throws Exception {

    }

    @Test
    public void testOnAttach() throws Exception {

    }

    @Test
    public void testOnCreate() throws Exception {

    }

    @Test
    public void testOnSaveInstanceState() throws Exception {

    }

    @Test
    public void testOnViewStateRestored() throws Exception {

    }

    @Test
    public void testOnCreateView() throws Exception {

    }

    @Test
    public void testOnActivityCreated() throws Exception {

    }

    @Test
    public void testOnStart() throws Exception {

    }

    @Test
    public void testOnStop() throws Exception {

    }

    @Test
    public void testSetUserVisibleHint() throws Exception {

    }

    @Test
    public void testOnDestroy() throws Exception {

    }

    @Test
    public void testOnPause() throws Exception {

    }

    @Test
    public void testOnResume() throws Exception {

    }

    @Test
    public void testGetRunState() throws Exception {

    }

    @Test
    public void testAddViewModel() throws Exception {

    }

    @Test
    public void testRemoveViewModel() throws Exception {

    }
}