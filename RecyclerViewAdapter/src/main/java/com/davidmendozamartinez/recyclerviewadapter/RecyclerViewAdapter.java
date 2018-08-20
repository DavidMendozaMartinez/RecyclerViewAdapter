package com.davidmendozamartinez.recyclerviewadapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.davidmendozamartinez.recyclerviewadapter.databinding.ItemBinding;
import com.davidmendozamartinez.recyclerviewadapter.model.Divider;

import java.util.ArrayList;

public class RecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private Divider divider;

    private ArrayList<T> list;
    private int itemLayoutId;
    private int variableId;

    private OnBindViewHolder<T> onBindViewHolder;
    private OnItemClickListener<T> clickListener;
    private OnItemLongClickListener<T> longClickListener;

    /**
     * @param list         Item list to show.
     * @param itemLayoutId Id of the item resource layout.
     * @param variableId   Name or Id of the item variable that we have assigned in the item resource layout.
     */
    public RecyclerViewAdapter(ArrayList<T> list, int itemLayoutId, int variableId) {
        this.list = list;
        this.itemLayoutId = itemLayoutId;
        this.variableId = variableId;

        this.divider = new Divider();
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (this.inflater == null) {
            this.inflater = LayoutInflater.from(parent.getContext());
        }

        return new RecyclerViewAdapter.ViewHolder(LayoutInflater.from(
                parent.getContext()).inflate(R.layout.item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewAdapter.ViewHolder holder, final int position) {
        final T item = list.get(position);

        ItemBinding itemBinding = holder.getBinding();

        if (itemBinding != null) {
            View view = inflater.inflate(itemLayoutId, null);

            if (variableId != 0) {
                ViewDataBinding binding = DataBindingUtil.bind(view);

                if (binding != null) {
                    binding.setVariable(variableId, item);
                }
            }

            itemBinding.itemContainer.removeAllViews();
            itemBinding.itemContainer.addView(view);

            itemBinding.itemContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (clickListener != null) {
                        clickListener.onItemClick(item);
                    }
                }
            });

            itemBinding.itemContainer.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    return longClickListener != null && longClickListener.onItemLongClick(item);
                }
            });

            itemBinding.itemDivider.setVisibility(divider != null ? View.VISIBLE : View.GONE);

            if (divider != null) {
                itemBinding.itemDivider.setBackgroundColor(divider.getColor());
                ConstraintLayout.LayoutParams dividerParams = (ConstraintLayout.LayoutParams) itemBinding.itemDivider.getLayoutParams();
                dividerParams.setMargins(divider.getLeft(), divider.getTop(), divider.getRight(), divider.getBottom());
                itemBinding.itemDivider.setLayoutParams(dividerParams);
            }
        }

        if (onBindViewHolder != null) {
            onBindViewHolder.onBindViewHolder(item, holder, position);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    /**
     * @param onBindViewHolder Interface to add extra functionality to the onBindViewHolder method.
     */
    public void addOnBindViewHolder(OnBindViewHolder<T> onBindViewHolder) {
        this.onBindViewHolder = onBindViewHolder;
    }

    /**
     * @param listener Interface definition for a callback to be invoked when a view is clicked.
     */
    public void setOnItemClickListener(OnItemClickListener<T> listener) {
        this.clickListener = listener;
    }

    /**
     * @param listener Interface definition for a callback to be invoked when a view has been clicked and held.
     */
    public void setOnItemLongClickListener(OnItemLongClickListener<T> listener) {
        this.longClickListener = listener;
    }

    /**
     * @param divider Divider that separates items from the list.
     */
    public void setDivider(Divider divider) {
        this.divider = divider;
    }

    public interface OnBindViewHolder<T> {
        void onBindViewHolder(T item, @NonNull RecyclerViewAdapter.ViewHolder holder, int position);
    }

    public interface OnItemClickListener<T> {
        void onItemClick(T item);
    }

    public interface OnItemLongClickListener<T> {
        boolean onItemLongClick(T item);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final View view;
        private final ItemBinding binding;

        private ViewHolder(View view) {
            super(view);
            this.view = view;
            this.binding = DataBindingUtil.bind(view);
        }

        public View getView() {
            return this.view;
        }

        public ItemBinding getBinding() {
            return this.binding;
        }
    }
}
