import 'package:flutter/material.dart';

class CustomButton extends StatelessWidget {
  final Function() onPressed;
  final String title;
  final Widget image;
  final Color? theme;

  const CustomButton({required this.onPressed, required this.title, required this.image, this.theme, super.key});

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
        onTap: onPressed,
        child: Container(
          padding: const EdgeInsets.all(3),
          margin: const EdgeInsets.all(6),
          child: Row(
            children: [
              Container(
                  margin: const EdgeInsets.only(right: 6),
                  padding: const EdgeInsets.all(3),
                  decoration: BoxDecoration(shape: BoxShape.circle, color: theme ?? Theme.of(context).primaryColor),
                  child: image),
              Flexible(
                  child: Text(
                    title,
                    style: const TextStyle(fontSize: 10),
                    maxLines: 1,
                    softWrap: false,
                    overflow: TextOverflow.fade,
                  ))
            ],
          ),
        ));
  }
}