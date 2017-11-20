package widgets.dinesh.com.paytmproject.addEntity.ui.dialogues;

import butterknife.OnClick;
import widgets.dinesh.com.paytmproject.R;
import widgets.dinesh.com.paytmproject.base.widgets.RxDialogFragment;

public class EntityAddedDialog extends RxDialogFragment {


    @OnClick(R.id.bt_ok)
    void onOkClicked(){
        dismiss();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.dialog_fragment_entity_added;
    }
}
