package uk.ac.tees.w9543466.pathlight.employer.works;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import uk.ac.tees.w9543466.pathlight.ListItemClickListener;
import uk.ac.tees.w9543466.pathlight.databinding.LayoutWorkItemBinding;

public class MyWorkAdapter extends ListAdapter<WorkItem, MyWorkAdapter.WorkVh> {

    private ListItemClickListener listener;

    public void setListener(ListItemClickListener listener) {
        this.listener = listener;
    }

    public MyWorkAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public WorkVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        LayoutWorkItemBinding view = LayoutWorkItemBinding.inflate(inflater, parent, false);
        return new WorkVh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkVh holder, int position) {
        holder.bindTo(getItem(position));
        holder.itemView.getRoot().setOnClickListener(v -> listener.onItemClick(position));
    }

    private static final DiffUtil.ItemCallback<WorkItem> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<WorkItem>() {
                @Override
                public boolean areItemsTheSame(
                        @NonNull WorkItem oldUser, @NonNull WorkItem newUser) {
                    return oldUser.getId() == newUser.getId();
                }

                @Override
                public boolean areContentsTheSame(
                        @NonNull WorkItem oldUser, @NonNull WorkItem newUser) {
                    return oldUser.equals(newUser);
                }
            };

    static class WorkVh extends RecyclerView.ViewHolder {
        private final LayoutWorkItemBinding itemView;

        public WorkVh(@NonNull LayoutWorkItemBinding itemView) {
            super(itemView.getRoot());
            this.itemView = itemView;
        }

        public void bindTo(WorkItem item) {
            itemView.setModel(item);
        }
    }
}
