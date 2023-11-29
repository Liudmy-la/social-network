import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { CommonModule, NgOptimizedImage } from '@angular/common';

import { INTEREST_CHIPS_NAMES, INTEREST_CHIPS_ICONS } from '../../../app.config';

@Component({
  selector: 'epm-chips',
  standalone: true,
  imports: [CommonModule, NgOptimizedImage],
  templateUrl: './epm-chips.component.html',
  styleUrls: ['./epm-chips.component.scss']
})
export class EpmChipsComponent {
  @Input() chips!: string;
  @Input() isEditable = false;
  @Input() isActive = false;

  @Output() editChipsStatus: EventEmitter<string> = new EventEmitter<string>();

  iconFileName: string = '';

  constructor() {}

  ngOnInit(): void {
    this.setIconFileName();
  }

  private setIconFileName(): void {
    const normalizedChips = this.chips.trim();
    const isChipsValid = INTEREST_CHIPS_NAMES.includes(normalizedChips);

    if (isChipsValid) {
      this.iconFileName = INTEREST_CHIPS_ICONS[normalizedChips] || '';
    }
  }

  onIsEditableClick(event: Event, chips: string): void {
    
    this.editChipsStatus.emit(chips);
  }

}
