package com.example.mentor;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {
    private List<Course> courseList;
    private Context context;
    private OnCourseDeleteListener deleteListener;

    public interface OnCourseDeleteListener {
        void onCourseDelete(int position);
    }

    public CourseAdapter(Context context, List<Course> courseList, OnCourseDeleteListener deleteListener) {
        this.context = context;
        this.courseList = courseList;
        this.deleteListener = deleteListener;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_course, parent, false);
        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        Course course = courseList.get(position);

        holder.titleTextView.setText(course.getTitle());
        holder.lessonsTextView.setText(String.format("Lessons: %d", course.getLessonsCount()));
        holder.descriptionTextView.setText(course.getDescription());

        Glide.with(holder.itemView.getContext())
                .load(course.getImageUrl())
                .placeholder(R.drawable.loading)
                .error(R.drawable.error)
                .into(holder.courseImage);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, CourseInfoActivity.class);
            intent.putExtra("courseTitle", course.getTitle());
            intent.putExtra("courseDescription", course.getDescription());
            intent.putExtra("lessonsCount", course.getLessonsCount());
            intent.putExtra("imageUrl", course.getImageUrl());
            intent.putStringArrayListExtra("modules", new ArrayList<>(course.getModules()));
            context.startActivity(intent);
        });

        holder.deleteButton.setOnClickListener(v -> {
            if (deleteListener != null) {
                deleteListener.onCourseDelete(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }

    static class CourseViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView, lessonsTextView, descriptionTextView;
        ImageView courseImage;
        ImageButton deleteButton;

        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.tvTitle);
            lessonsTextView = itemView.findViewById(R.id.tvLessons);
            descriptionTextView = itemView.findViewById(R.id.tvDescription);
            courseImage = itemView.findViewById(R.id.imageView);
            deleteButton = itemView.findViewById(R.id.deleteCourseButton);
        }
    }
}