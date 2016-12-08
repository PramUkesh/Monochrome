package james.monochrome.data.items;

import android.content.Context;

import james.monochrome.R;
import james.monochrome.data.PositionData;
import james.monochrome.utils.ItemUtils;
import james.monochrome.utils.TileUtils;

public class PumpkinItemData extends ItemData {

    public PumpkinItemData(Context context, boolean hasPickedUp, boolean isHolding) {
        super(context, TileUtils.TILE_PUMPKIN, hasPickedUp, isHolding);
    }

    public PumpkinItemData(Context context, PositionData position) {
        super(context, TileUtils.TILE_PUMPKIN, position);
    }

    @Override
    public String getName() {
        return getContext().getString(R.string.item_pumpkin);
    }

    @Override
    public String getDescription() {
        return getContext().getString(R.string.item_pumpkin_desc);
    }

    @Override
    String getId() {
        return ItemUtils.KEY_ITEM_PUMPKIN;
    }

    @Override
    public String getKey() {
        return ItemUtils.KEY_ITEM_PUMPKIN;
    }

    @Override
    public int getVolume() {
        return 20;
    }

    @Override
    boolean hasConstantPosition() {
        return false;
    }

    @Override
    public void onUse() {
    }
}
