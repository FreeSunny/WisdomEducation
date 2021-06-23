//
//  NEEduRtcJoinChannelParam.m
//  EduLogic
//
//  Created by Groot on 2021/5/20.
//  Copyright © 2021 NetEase. All rights reserved.
//  Use of this source code is governed by a MIT license that can be found in the LICENSE file
//

#import "NEEduRtcJoinChannelParam.h"

@implementation NEEduRtcJoinChannelParam
- (instancetype)init
{
    self = [super init];
    if (self) {
        self.subscribeAudio = YES;
        self.subscribeVideo = YES;
    }
    return self;
}
@end
