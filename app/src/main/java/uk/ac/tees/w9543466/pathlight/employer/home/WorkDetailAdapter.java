package uk.ac.tees.w9543466.pathlight.employer.home;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import uk.ac.tees.w9543466.pathlight.databinding.LayoutWorkDetailItemBinding;
import uk.ac.tees.w9543466.pathlight.KeyValueModel;

public class WorkDetailAdapter extends ListAdapter<KeyValueModel, WorkDetailAdapter.WorkDetailVh> {

    public WorkDetailAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public WorkDetailVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        LayoutWorkDetailItemBinding view = LayoutWorkDetailItemBinding.inflate(inflater, parent, false);
        return new WorkDetailVh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkDetailVh holder, int position) {
        holder.bindTo(getItem(position));
    }

    private static final DiffUtil.ItemCallback<KeyValueModel> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<KeyValueModel>() {
                @Override
                public boolean areItemsTheSame(
                        @NonNull KeyValueModel oldUser, @NonNull KeyValueModel newUser) {
                    return oldUser.getLabel().equals(newUser.getLabel());
                }

                @Override
                public boolean areContentsTheSame(
                        @NonNull KeyValueModel oldUser, @NonNull KeyValueModel newUser) {
                    return oldUser.equals(newUser);
                }
            };

    static class WorkDetailVh extends RecyclerView.ViewHolder {
        private final LayoutWorkDetailItemBinding itemView;

        public WorkDetailVh(@NonNull LayoutWorkDetailItemBinding itemView) {
            super(itemView.getRoot());
            this.itemView = itemView;
        }

        public void bindTo(KeyValueModel item) {
            itemView.setModel(item);
        }
    }
}
