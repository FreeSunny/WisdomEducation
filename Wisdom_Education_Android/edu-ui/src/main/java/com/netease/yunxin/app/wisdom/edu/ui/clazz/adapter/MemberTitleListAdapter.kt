/*
 * Copyright (c) 2021 NetEase, Inc.  All rights reserved.
 * Use of this source code is governed by a MIT license that can be found in the LICENSE file.
 */

package com.netease.yunxin.app.wisdom.edu.ui.clazz.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.netease.yunxin.app.wisdom.edu.logic.model.NEEduMember
import com.netease.yunxin.app.wisdom.edu.ui.databinding.ItemMemberTextListBinding

class MemberTitleListAdapter(
    val context: Context,
    dataList: MutableList<NEEduMember>,
    listener: OnItemClickListener<NEEduMember>? = null,
) : BaseAdapter<NEEduMember>(dataList, listener) {
    init {
        setDelegate(object : BaseDelegate<NEEduMember>() {
            override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder<*>? {
                val binding = ItemMemberTextListBinding.inflate(LayoutInflater.from(context), parent, false)
                return MemberViewHolder(binding)
            }

            override fun getItemViewType(data: NEEduMember, pos: Int): Int {
                return 0
            }
        })
    }

    inner class MemberViewHolder(val binding: ItemMemberTextListBinding) : BaseViewHolder<NEEduMember>(binding.root) {
        override fun findViews() {
        }

        override fun onBindViewHolder(item: NEEduMember) {
            binding.tvMemberName.text = item.userName
        }
    }
}

