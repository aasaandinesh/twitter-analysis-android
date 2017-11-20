package widgets.dinesh.com.paytmproject;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.view.MenuItem;
import android.widget.Button;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowIntent;

import widgets.dinesh.com.paytmproject.addEntity.ui.activities.AddTwitterEntityActivity;
import widgets.dinesh.com.paytmproject.info.InfoActivity;

import static junit.framework.Assert.assertEquals;
import static org.robolectric.Shadows.shadowOf;

/**
 * Created by ajmac1005 on 20/11/17.
 */
@RunWith(RobolectricTestRunner.class)
public class TwitterAddEntityActivityTest {
    AddTwitterEntityActivity activity;

    @Before
    public void setup(){
        activity = Robolectric.setupActivity(AddTwitterEntityActivity.class);
    }

    @Test
    public void checkForCorrectTitle(){
        assertEquals("Add Entity", activity.getTitle());
    }

    @Test
    public void checkIfEditTextAppearsWithCorrectHint(){
        TextInputLayout til = activity.findView(R.id.til_keyword);
        assertEquals("Enter Keyword to be analysed", til.getHint().toString());
    }

    @Test
    public void checkIfAddButtonAppearaWithCorrectText(){
        Button button = activity.findView(R.id.bt_add_entity);
        assertEquals("Add", button.getText().toString());
    }

    @Test
    public void checkIfInfoClickOpensInfoActivity(){
        MenuItem infoMenuItem = shadowOf(activity).getOptionsMenu().findItem(R.id.action_info);
        activity.onOptionsItemSelected(infoMenuItem);

        Intent startedIntent = shadowOf(activity).getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startedIntent);
        assertEquals(InfoActivity.class, shadowIntent.getIntentClass());
    }
}
