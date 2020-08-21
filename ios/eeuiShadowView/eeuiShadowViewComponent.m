//
//  eeuiShadowViewComponent.m
//  Pods
//

#import "eeuiShadowViewComponent.h"
#import "BaseShadowView.h"
#import "DeviceUtil.h"
#import <WeexPluginLoader/WeexPluginLoader.h>

@interface eeuiShadowViewComponent ()

@property(nonatomic, assign) float blur;
@property(nonatomic, strong) NSString *color;
@property(nonatomic, strong) NSString *backgroundColor;
@property(nonatomic, assign) float offsetX;
@property(nonatomic, assign) float offsetY;
@property(nonatomic, strong) NSString *placement;

@end

@implementation eeuiShadowViewComponent

WX_PlUGIN_EXPORT_COMPONENT(shadow-view, eeuiShadowViewComponent)


- (instancetype)initWithRef:(NSString *)ref type:(NSString *)type styles:(NSDictionary *)styles attributes:(NSDictionary *)attributes events:(NSArray *)events weexInstance:(WXSDKInstance *)weexInstance {
    self = [super initWithRef:ref type:type styles:styles attributes:attributes events:events weexInstance:weexInstance];
    if (self) {
        _blur = 10.f;
        _color = @"#000000";
        _backgroundColor = @"#ffffff";
        _offsetX = 0.f;
        _offsetY = 0.f;
        _placement = @"all";

        for (NSString *key in styles.allKeys) {
            [self dataKey:key value:styles[key]];
        }
        for (NSString *key in attributes.allKeys) {
            [self dataKey:key value:attributes[key]];
        }
    }
    return self;
}

- (UIView *)loadView {
    return [[BaseShadowView alloc] init];
}

- (void)viewDidLoad {
    [super viewDidLoad];
    [self updateView];
}

- (void)insertSubview:(WXComponent *)subcomponent atIndex:(NSInteger)index {
//    [self.view addSubview:subcomponent.view];
    [super insertSubview:subcomponent atIndex:index];
    [(BaseShadowView *) self.view backToBack];
}


- (void)updateStyles:(NSDictionary *)styles {
    for (NSString *key in styles.allKeys) {
        [self dataKey:key value:styles[key]];
    }
    [self updateView];
}

- (void)updateAttributes:(NSDictionary *)attributes {
    for (NSString *key in attributes.allKeys) {
        [self dataKey:key value:attributes[key]];
    }
    [self updateView];
}


#pragma mark data

- (void)dataKey:(NSString *)key value:(id)value {
    key = [DeviceUtil convertToCamelCaseFromSnakeCase:key];
    if ([key isEqualToString:@"eeui"] && [value isKindOfClass:[NSDictionary class]]) {
        NSArray *keys = [value allKeys];
        for (NSString *k in keys) {
            [self dataKey:k value:value[k]];
        }
    } else if ([key isEqualToString:@"blur"]) {
        _blur = (float) [WXConvert CGFloat:value];
    } else if ([key isEqualToString:@"color"]) {
        _color = [WXConvert NSString:value];
    } else if ([key isEqualToString:@"backgroundColor"]) {
        _backgroundColor = [WXConvert NSString:value];
    } else if ([key isEqualToString:@"offsetX"]) {
        _offsetX = (float) [WXConvert CGFloat:value];
    } else if ([key isEqualToString:@"offsetY"]) {
        _offsetY = (float) [WXConvert CGFloat:value];
    } else if ([key isEqualToString:@"placement"]) {
        _placement = [WXConvert NSString:value];
    }
}

- (void)updateView {
    YCShadowSide placementVal = nil;
    if ([_placement containsString:@"left"]) {
        placementVal = YCShadowSideLeft | placementVal;
    }
    if ([_placement containsString:@"right"]) {
        placementVal = YCShadowSideRight | placementVal;
    }
    if ([_placement containsString:@"top"]) {
        placementVal = YCShadowSideTop | placementVal;
    }
    if ([_placement containsString:@"bottom"]) {
        placementVal = YCShadowSideBottom | placementVal;
    }
    if (_placement.length == 0 || [_placement containsString:@"all"]) {
        placementVal = YCShadowSideAllSides;
    }
    BaseShadowView *shadowView = (BaseShadowView *) self.view;
    shadowView.backgroundColor = [WXConvert UIColor:_backgroundColor];
    [shadowView yc_shaodwRadius:SCALEFLOAT(_blur) shadowColor:[WXConvert UIColor:_color] shadowOffset:CGSizeMake(SCALEFLOAT(_offsetX), SCALEFLOAT(_offsetY)) byShadowSide:(placementVal)];
}

@end
