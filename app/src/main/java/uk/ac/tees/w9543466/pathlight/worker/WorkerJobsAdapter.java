package uk.ac.tees.w9543466.pathlight.worker;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import uk.ac.tees.w9543466.pathlight.ListItemClickListener;
import uk.ac.tees.w9543466.pathlight.databinding.LayoutWorkerJobItemBinding;

public class WorkerJobsAdapter extends ListAdapter<WorkDto, WorkerJobsAdapter.WorkerJobVh> {

    private ListItemClickListener listener;

    public void setListener(ListItemClickListener listener) {
        this.listener = listener;
    }

    public WorkerJobsAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public WorkerJobVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        LayoutWorkerJobItemBinding view = LayoutWorkerJobItemBinding.inflate(inflater, parent, false);
        return new WorkerJobVh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkerJobVh holder, int position) {
        holder.bindTo(getItem(position));
        holder.itemView.btnContainer.button2.setOnClickListener(v -> listener.onItemClick(position));
    }

    private static final DiffUtil.ItemCallback<WorkDto> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<WorkDto>() {
                @Override
                public boolean areItemsTheSame(
                        @NonNull WorkDto oldUser, @NonNull WorkDto newUser) {
                    return oldUser.getId() == newUser.getId();
                }

                @Override
                public boolean areContentsTheSame(
                        @NonNull WorkDto oldUser, @NonNull WorkDto newUser) {
                    return oldUser.equals(newUser);
                }
            };

    static class WorkerJobVh extends RecyclerView.ViewHolder {
        private final LayoutWorkerJobItemBinding itemView;

        public WorkerJobVh(@NonNull LayoutWorkerJobItemBinding itemView) {
            super(itemView.getRoot());
            this.itemView = itemView;
        }

        public void bindTo(WorkDto item) {
            itemView.setModel(item);
        }
    }
}
