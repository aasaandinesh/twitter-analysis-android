package widgets.dinesh.com.paytmproject;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.view.MenuItem;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowIntent;

import widgets.dinesh.com.paytmproject.addEntity.ui.activities.AddTwitterEntityActivity;
import widgets.dinesh.com.paytmproject.info.InfoActivity;
import widgets.dinesh.com.paytmproject.twitterList.ui.activities.TwitterListActivity;

import static junit.framework.TestCase.assertEquals;
import static org.robolectric.Shadows.shadowOf;


/**
 * Created by ajmac1005 on 17/11/17.
 */

@RunWith(RobolectricTestRunner.class)
public class TwitterListActivityTest {
    TwitterListActivity activity;

    @Before
    public void setup(){
        activity = Robolectric.setupActivity(TwitterListActivity.class);
    }


    @Test
    public void checkForCorrectTitle(){
        assertEquals("Keywords", activity.getTitle());
    }

    @Test
    public void checkIfInfoClickOpensInfoActivity(){
        MenuItem infoMenuItem = shadowOf(activity).getOptionsMenu().findItem(R.id.action_info);
        activity.onOptionsItemSelected(infoMenuItem);

        Intent startedIntent = shadowOf(activity).getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startedIntent);
        assertEquals(InfoActivity.class, shadowIntent.getIntentClass());
    }

    @Test
    public void checkIfFabClickOpensAddActivity(){
        FloatingActionButton fab = activity.findView(R.id.fab_add_entity);
        fab.performClick();
        Intent startedIntent = shadowOf(activity).getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startedIntent);
        assertEquals(AddTwitterEntityActivity.class, shadowIntent.getIntentClass());
    }

}
