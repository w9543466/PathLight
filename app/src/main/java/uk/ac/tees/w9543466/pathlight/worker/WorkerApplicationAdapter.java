package uk.ac.tees.w9543466.pathlight.worker;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Objects;

import uk.ac.tees.w9543466.pathlight.databinding.LayoutWorkerApplicationItemBinding;
import uk.ac.tees.w9543466.pathlight.worker.apimodel.WorkerApplicationItem;

public class WorkerApplicationAdapter extends ListAdapter<WorkerApplicationItem, WorkerApplicationAdapter.WorkerApplicationVh> {

    public WorkerApplicationAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public WorkerApplicationVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        LayoutWorkerApplicationItemBinding view = LayoutWorkerApplicationItemBinding.inflate(inflater, parent, false);
        return new WorkerApplicationVh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkerApplicationVh holder, int position) {
        holder.bindTo(getItem(position));
    }

    private static final DiffUtil.ItemCallback<WorkerApplicationItem> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<WorkerApplicationItem>() {
                @Override
                public boolean areItemsTheSame(
                        @NonNull WorkerApplicationItem oldUser, @NonNull WorkerApplicationItem newUser) {
                    return Objects.equals(oldUser.getId(), newUser.getId());
                }

                @Override
                public boolean areContentsTheSame(
                        @NonNull WorkerApplicationItem oldUser, @NonNull WorkerApplicationItem newUser) {
                    return oldUser.equals(newUser);
                }
            };

    static class WorkerApplicationVh extends RecyclerView.ViewHolder {
        private final LayoutWorkerApplicationItemBinding itemView;

        public WorkerApplicationVh(@NonNull LayoutWorkerApplicationItemBinding itemView) {
            super(itemView.getRoot());
            this.itemView = itemView;
        }

        public void bindTo(WorkerApplicationItem item) {
            itemView.setModel(item);
        }
    }
}