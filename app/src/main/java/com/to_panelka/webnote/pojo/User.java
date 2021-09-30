package com.to_panelka.webnote.pojo;

import androidx.annotation.Nullable;

public class User {
    private long id;
    private String imageUrl;
    private String name;
    private String description;
    private int followingCount;
    private int followersCount;

    public User(long id,
                String imageUrl,
                String name,
                String description,
                int followingCount,
                int followersCount) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.name = name;
        this.description = description;
        this.followingCount = followingCount;
        this.followersCount = followersCount;
    }

    public long getId() {
        return id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getFollowingCount() {
        return followingCount;
    }

    public int getFollowersCount() {
        return followersCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (followingCount != user.followingCount) return false;
        if (followersCount != user.followersCount) return false;
        if (imageUrl != null ? !imageUrl.equals(user.imageUrl) : user.imageUrl != null)
            return false;
        if (!name.equals(user.name)) return false;
        if (description != null ? !description.equals(user.description) : user.description != null) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (imageUrl != null ? imageUrl.hashCode() : 0);
        result = 31 * result + name.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + followingCount;
        result = 31 * result + followersCount;
        return result;
    }
}
