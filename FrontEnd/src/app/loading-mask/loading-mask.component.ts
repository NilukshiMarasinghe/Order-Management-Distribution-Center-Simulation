import { Component, OnInit } from "@angular/core";

@Component({
  selector: "app-loading-mask",
  templateUrl: "./loading-mask.component.html",
  styleUrls: ["./loading-mask.component.css"],
})
export class LoadingMaskComponent implements OnInit {
  public lottieConfig: Object;

  constructor() {
    this.lottieConfig = {
      path: "assets/lottie/loading-adidi.json",
      autoplay: true,
      loop: true,
      rendererSettings: {
        preserveAspectRatio: "xMidYMid slice",
      },
    };
  }

  ngOnInit(): void {}
}
