package uk.ac.tees.w9543466.pathlight.employer.applications;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import uk.ac.tees.w9543466.pathlight.databinding.LayoutApplicationsItemBinding;

public class ApplicationsAdapter extends ListAdapter<WorkApplication, ApplicationsAdapter.ApplicationVh> {

    public ApplicationsAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public ApplicationVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        LayoutApplicationsItemBinding view = LayoutApplicationsItemBinding.inflate(inflater, parent, false);
        return new ApplicationVh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ApplicationVh holder, int position) {
        holder.bindTo(getItem(position));
    }

    private static final DiffUtil.ItemCallback<WorkApplication> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<WorkApplication>() {
                @Override
                public boolean areItemsTheSame(
                        @NonNull WorkApplication oldUser, @NonNull WorkApplication newUser) {
                    return oldUser.getId().equals(newUser.getId());
                }

                @Override
                public boolean areContentsTheSame(
                        @NonNull WorkApplication oldUser, @NonNull WorkApplication newUser) {
                    return oldUser.equals(newUser);
                }
            };

    static class ApplicationVh extends RecyclerView.ViewHolder {
        private final LayoutApplicationsItemBinding itemView;

        public ApplicationVh(@NonNull LayoutApplicationsItemBinding itemView) {
            super(itemView.getRoot());
            this.itemView = itemView;
        }

        public void bindTo(WorkApplication item) {
            itemView.setModel(item);
        }
    }
}