package com.gomeos.mvvm.viewmodel.proxy;

import android.support.v4.app.FragmentManager;

import com.gomeos.mvvm.view.ui.BaseDialogFragment;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by wwish on 16/11/10.
 */
public class DialogProxyTest {

    DialogProxy mDialogProxy;

    @Before
    public void setUp() throws Exception {

        mDialogProxy = mock(DialogProxy.class);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void show() throws Exception {

        Field field = DialogProxy.class.getDeclaredField("dialogFragment");
        field.setAccessible(true);
        BaseDialogFragment dialogFragment = (BaseDialogFragment) field.get(mDialogProxy);

        Field field2 = DialogProxy.class.getDeclaredField("fragmentManager");
        field2.setAccessible(true);
        FragmentManager fragmentManager = (FragmentManager) field.get(mDialogProxy);

        Field field3 = DialogProxy.class.getDeclaredField("tag");
        field3.setAccessible(true);
        String tag = (String) field.get(mDialogProxy);

        mDialogProxy.show();
        verify(mDialogProxy).show();

    }

    @Test
    public void dismiss() throws Exception {

        Field field = DialogProxy.class.getDeclaredField("dialogFragment");
        field.setAccessible(true);
        BaseDialogFragment dialogFragment = (BaseDialogFragment) field.get(mDialogProxy);
        mDialogProxy.dismiss();
        verify(mDialogProxy).dismiss();

    }

    @Test
    public void getDialogFragment() throws Exception {

    }

}