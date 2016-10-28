package james.monochrome.dialogs;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import james.monochrome.Monochrome;
import james.monochrome.R;
import james.monochrome.adapters.ItemAdapter;
import james.monochrome.data.items.ItemData;
import james.monochrome.data.tiles.TileData;
import james.monochrome.utils.ItemUtils;

public class ChestDialog extends AppCompatDialog implements Monochrome.OnSomethingHappenedListener {

    private List<OnDismissListener> listeners;

    private RecyclerView holding, chest;
    private ItemAdapter holdingAdapter, chestAdapter;
    private Monochrome monochrome;

    public ChestDialog(Context context) {
        super(context, R.style.AppTheme_Dialog_FullScreen);
        monochrome = (Monochrome) context.getApplicationContext();

        listeners = new ArrayList<>();
        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                for (OnDismissListener listener : listeners) {
                    listener.onDismiss(dialog);
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_chest);

        holding = (RecyclerView) findViewById(R.id.holding);
        holding.setLayoutManager(new GridLayoutManager(getContext(), 3));

        holdingAdapter = new ItemAdapter(getContext(), ItemUtils.getHoldingItems(getContext()), false);
        holding.setAdapter(holdingAdapter);

        chest = (RecyclerView) findViewById(R.id.chest);
        chest.setLayoutManager(new GridLayoutManager(getContext(), 3));

        chestAdapter = new ItemAdapter(getContext(), ItemUtils.getChestItems(getContext()), true);
        chest.setAdapter(chestAdapter);

        addOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                monochrome.removeListener(ChestDialog.this);
            }
        });

        monochrome.addListener(this);
    }

    @Override
    public void onTileChange(TileData tile) {
    }

    @Override
    public void onRequestTileKeyChange(TileData tile, int tileKey) {
    }

    @Override
    public void onRequestMapChange(String mapKey) {
    }

    @Override
    public void onRequestPositionSave() {
    }

    @Override
    public void onRequestShake() {
    }

    @Override
    public void onItemMoved(ItemData item) {
        if (holding != null && chest != null) {
            holdingAdapter = new ItemAdapter(getContext(), ItemUtils.getHoldingItems(getContext()), false);
            holding.setAdapter(holdingAdapter);

            chestAdapter = new ItemAdapter(getContext(), ItemUtils.getChestItems(getContext()), true);
            chest.setAdapter(chestAdapter);
        }
    }

    public void addOnDismissListener(OnDismissListener listener) {
        listeners.add(listener);
    }
}