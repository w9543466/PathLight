package uk.ac.tees.w9543466.pathlight.employer.profile;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import uk.ac.tees.w9543466.pathlight.databinding.LayoutProfileDetailItemBinding;
import uk.ac.tees.w9543466.pathlight.employer.KeyValueModel;

public class ProfileDetailAdapter extends ListAdapter<KeyValueModel, ProfileDetailAdapter.WorkDetailVh> {

    public ProfileDetailAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public WorkDetailVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        LayoutProfileDetailItemBinding view = LayoutProfileDetailItemBinding.inflate(inflater, parent, false);
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
        private final LayoutProfileDetailItemBinding itemView;

        public WorkDetailVh(@NonNull LayoutProfileDetailItemBinding itemView) {
            super(itemView.getRoot());
            this.itemView = itemView;
        }

        public void bindTo(KeyValueModel item) {
            itemView.setModel(item);
        }
    }
}
