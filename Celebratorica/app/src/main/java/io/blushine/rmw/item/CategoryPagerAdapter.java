package io.blushine.rmw.item;


import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Adapter for switching between item lists
 */
class CategoryPagerAdapter extends FragmentPagerAdapter {
private List<Category> mCategories = new ArrayList<>();

CategoryPagerAdapter(@NonNull FragmentManager fragmentManager) {
	super(fragmentManager);
}

@Override
public Fragment getItem(int position) {
	String categoryId = mCategories.get(position).getId();
	CategoryPageFragment fragment = new CategoryPageFragment();
	fragment.setArguments(categoryId);
	return fragment;
}

@Override
public void restoreState(Parcelable state, ClassLoader loader) {
	super.restoreState(state, loader);
}

@Override
public int getCount() {
	return mCategories.size();
}

@Override
public CharSequence getPageTitle(int position) {
	Category category = mCategories.get(position);
	return category.getName();
}

/**
 * Add a category sorted by the order
 * @param newCategory the category to add
 */
void addItem(Category newCategory) {
	boolean added = false;
	
	for (int i = 0; i < mCategories.size(); ++i) {
		Category currentCategory = mCategories.get(i);
		
		boolean isNewNewerThanCurrentItem = currentCategory.compareTo(newCategory) > 0;
		if (isNewNewerThanCurrentItem) {
			mCategories.add(i, newCategory);
			added = true;
			break;
		}
	}
	
	// Add to the end of the list
	if (!added) {
		mCategories.add(newCategory);
	}
	
	notifyDataSetChanged();
}

/**
 * Set the categories
 * @param categories list of all categories
 */
void setItems(List<Category> categories) {
	mCategories.addAll(categories);
	notifyDataSetChanged();
}

/**
 * Remove a category
 * @param category the category to remove
 */
void removeItem(Category category) {
	mCategories.remove(category);
	notifyDataSetChanged();
}

/**
 * Sort categories. Call this after you've changed the {@link Category#order} of several items
 */
void sortItems() {
	Collections.sort(mCategories);
}

/**
 * Get the category in the specified position
 * @param position get the category in this position
 * @return category in the specified position, null if no categories exist
 */
Category getCategory(int position) {
	if (!mCategories.isEmpty()) {
		return mCategories.get(position);
	} else {
		return null;
	}
}
}