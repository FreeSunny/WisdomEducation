//
//  NEEduChatMessage.h
//  EduUI
//
//  Created by Groot on 2021/5/25.
//  Copyright © 2021 NetEase. All rights reserved.
//  Use of this source code is governed by a MIT license that can be found in the LICENSE file
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

typedef NS_ENUM(NSInteger,NEEduChatMessageType) {
    NEEduChatMessageTypeText,
    NEEduChatMessageTypeTime,
};

@interface NEEduChatMessage : NSObject
@property (nonatomic, strong) NSString *userName;
@property (nonatomic, strong) NSString *content;
@property (nonatomic, assign) CGSize textSize;
@property (nonatomic, assign) BOOL myself;
@property (nonatomic, assign) NEEduChatMessageType type;
@property (nonatomic, assign) NSTimeInterval timestamp;
@end

NS_ASSUME_NONNULL_END
