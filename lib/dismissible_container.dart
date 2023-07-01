import 'package:flutter/cupertino.dart';
import 'package:flutter/services.dart';
import 'package:wearable_flutter_fragment_application/wearable_fragment_application_observers.dart';

class DismissibleContainer extends StatefulWidget {
  final bool isDismissible;
  final Widget child;

  const DismissibleContainer({required this.child, this.isDismissible = true, super.key});

  @override
  State<StatefulWidget> createState() => _DismissibleContainer();
}

class _DismissibleContainer extends State<DismissibleContainer> with RouteAware {
  final methodChannel = const MethodChannel("WearableFragmentApplication");
  var _hasPageLeft = false;

  @override
  initState() {
    _updateDismissibleState();
    super.initState();
  }

  @override
  didUpdateWidget(DismissibleContainer oldWidget) {
    _updateDismissibleState();
    super.didUpdateWidget(oldWidget);
  }

  @override
  void didChangeDependencies() {
    super.didChangeDependencies();
    WearableFragmentApplication().dismissibleContainerObserver.subscribe(this, ModalRoute.of(context)!);
  }

  @override
  void dispose() {
    WearableFragmentApplication().dismissibleContainerObserver.unsubscribe(this);
    super.dispose();
  }

  @override
  void didPushNext() {
    _changePageLeftState(true);
    super.didPushNext();
  }

  @override
  void didPopNext() {
    _changePageLeftState(false);
    super.didPopNext();
  }

  @override
  void didPop() {
    _changePageLeftState(true);
    super.didPop();
  }

  void _updateDismissibleState() {
    _sendPageLeftState(!widget.isDismissible ? true : _hasPageLeft);
  }

  void _changePageLeftState(bool newState) {
    _hasPageLeft = newState;
    if (widget.isDismissible) {
      _sendPageLeftState(newState);
    }
  }

  void _sendPageLeftState(bool newState) {
    methodChannel.invokeMethod("hasPageLeft", newState);
  }

  @override
  Widget build(BuildContext context) {
    return WillPopScope(onWillPop: () async {
      return !widget.isDismissible;
    }, child: widget.child);
  }

}